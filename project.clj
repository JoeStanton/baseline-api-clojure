(defproject baseline-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.trace "0.7.6"]
                 [compojure "1.1.6"]
                 [ring/ring-json "0.2.0"]
                 [http-kit "2.1.16"]
                 [lein-ring "0.8.11"]
                 [com.datomic/datomic-free "0.9.4815.12"]
                ]
  :plugins [ [quickie "0.2.5"] ]
  :main ^:skip-aot baseline-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
