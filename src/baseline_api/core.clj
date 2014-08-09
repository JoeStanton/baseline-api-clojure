(ns baseline-api.core
  (:use compojure.core)
  (:use ring.middleware.json)
  (:use ring.util.response)
  (:use org.httpkit.server)
  (:use org.httpkit.timer)
  (:use [datomic.api :only  [q db] :as d])
  (:require  [compojure.route :as route]
             [baseline-api.sample :as sample]
             [baseline-api.schema :as schema]
            ))

(def system
  "The main store of state"
  (atom { :services [sample/example-service sample/example-service-2]
         :hosts [sample/web-01 sample/db-01]
         :events [sample/example-deployment]}))

(defn find-by [key-identifier value] (first (filter (fn [system] (= (system key-identifier) value)) (:hosts system))))

(defn async-handler [ring-request]
  (with-channel ring-request channel
    (add-watch system :update (fn [k r os ns] (send! channel {:body (str ns)})))))

(def base-url "http://localhost:8080")
(defn respond-with [body] (if body {:body body} {:status 404 :body {:error "Resource not found"}}))

(defroutes api-routes
  (GET "/" [] {:body {
                      :services (str base-url "/services/")
                      :hosts (str base-url "/hosts/")
                      :events (str base-url "/events/")
                      :notifications (str base-url "/notifications/")
                      }})
  (GET "/services/" [] {:body (:services system)})
  (GET "/hosts/" [] {:body (:hosts system)})
  (GET "/hosts/:hostname" [hostname] (respond-with (find-by :hostname hostname)))
  (PUT "/hosts/:hostname" [hostname :as request] (update-in system :hosts (fn [hosts] (into [] (remove #((= (% :hostname) hostname)) hosts)))))
  (DELETE "/hosts/:hostname" [hostname] (update-in system :hosts (fn [hosts] (into [] (remove #((= (% :hostname) hostname)) hosts)))))
  (GET "/events/" [] {:body (:events system)})
  (GET "/notifications/" [] async-handler)
  (route/not-found (respond-with nil)))

(defn wrap-cors
  "Allow requests from all origins"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response
                 [:headers "Access-Control-Allow-Origin"]
                 (fn [_] "*")))))

(def api (-> #'api-routes
             (wrap-json-body {:keywords? true})
             (wrap-json-response)
             (wrap-cors)))

(defn -main [] (run-server api {:port 8080}))
