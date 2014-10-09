(ns baseline-api.sample)
; Data
(def nginx { :name "Nginx" :type :web-server :version "1.4.1" :status :ok :dependencies [] :metrics [] :logs [] })
(def unicorn { :name "Unicorn" :type :application-server :version nil :status :ok :dependencies [] :metrics [] :logs [] })
(def app { :name "Rails" :type :application :version "0394a" :status :ok :dependencies [] :metrics [] :logs [] })
(def postgres { :name "Postgres" :type :database :version nil :status :ok :dependencies [] :metrics [] :logs [] })

(def web-01 {
             :hostname "rb-production-web"
             :ip "192.168.1.100"
             :service "badger-time"
             :components [nginx unicorn app]
             :metrics { :type "graphite" :prefix "badger-time.production.rb-production01" }
             :environment :production
             :tags [:digital-ocean]
             })

(def db-01 {
            :hostname "rb-production-db"
            :ip "192.168.1.200"
            :service "badger-time"
            :components [postgres]
            :metrics { :type "graphite" :prefix "badger-time.production.rb-production01" }
            :environment :production
            :tags [:digital-ocean]
            })

(def example-service {
                      :id "badger-time"
                      :name "Badger Time"
                      :description "Internal resourcing tool at Red Badger"
                      :status :ok
                      :events []
                      :metrics { :type "graphite" :prefix "badger-time" }
                      :logs []
                      :components [nginx unicorn app postgres]
                      :hosts [web-01 db-01]
                      })

(def example-service-2 {
                        :id "insight"
                        :name "Insight"
                        :description "Search engine portal and crawler infrastructure"
                        :status :error
                        :events []
                        :metrics { :type "graphite" :prefix "insight" }
                        :logs []
                        :components []
                        })

(def example-deployment { :name "Deployment (badger-time v0.7.1)", :rel "github", :url "http://github.com/badger-time/commit/ababa" })
(def nginx-upgrade { :name "Ansible provisioner run (badger-time-infrastructure)", :rel "github", :url "http://github.com/badger-time-infrastructure/commit/ababa" })

