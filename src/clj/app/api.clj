(ns app.api
  (:require [tailrecursion.castra :refer [defrpc]]))

(def options [{:title "core.async"
               :description "asynchronous programming using channels"}
              {:title "core.logic"
               :description "embedded logic language like Prolog"}
              {:title "core.typed"
               :description "gradual typing for clojure"}
              {:title "datomic"
               :description "immutable database with time support and datalog query language"}
              {:title "instaparse"
               :description "make grammar-based parsing as easy to use as regexes"}
              {:title "clojure-sql and yesql"
               :description "my two favourite database access libraries"}
              {:title "hoplon"
               :description "Clojure/ClojureScript web framework based on spreadsheet-like cells and HLisp"}
              {:title "core.match"
               :description "pattern matching as a library"}
              {:title "overtone"
               :description "clojure wrapper for supercollider (making noises)"}])

(def all-votes (atom {}))

(defrpc get-state []
  {:options options
   :all-votes @all-votes})

(defrpc vote! [title direction]
  (swap! all-votes update-in [title]
         (case direction
           :up (fnil inc 0)
           :down dec))
  (get-state))

