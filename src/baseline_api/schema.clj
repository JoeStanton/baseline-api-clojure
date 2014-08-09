(ns baseline-api.schema)
(def service [
 {
  :db/id #db/id[:db.part/db]
  :db/ident :service/name
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db
  }

 {
  :db/id #db/id[:db.part/db]
  :db/ident :service/description
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db
  }

 {
  :db/id #db/id[:db.part/db]
  :db/ident :service/status
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db
  }
])
