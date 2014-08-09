(ns baseline-api.db)

(defn get-db-connection
  "Obtain a connection to datomic"
  []
  (let [uri (str "datomic:mem://" "baseline-api")]
    (d/create-database uri)
    (d/connect uri)))

(defn get-database [] (d/db (get-db-connection)))

; Query Interfaces

(defn all-services [db]
  (d/q '[:find ?e :where [?e :service/name]] db))

(defn create-service [db service]
  (d/transact db
              [{:db/id #db/id [:db.part/service]
                :service/name (service :name)
                :service/description (service :description)
                :service/status (service :status)}]))
