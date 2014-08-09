(ns baseline-api.core-test
  (:require [clojure.test :refer :all]
            [baseline-api.core :refer :all]))

(deftest listing-services
  (testing "Listing services"
    (is (= 0 (count (all-services (get-database)))))))

(deftest new-service
  (testing "Create a service"
    (create-service
      (get-db-connection)
      {:name "Baseline API" :description "Real-time monitoring tool." :status :ok})
    (is (= 1 (count (all-services (get-database)))))))
