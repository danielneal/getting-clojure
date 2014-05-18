(ns app.api
  (:require [tailrecursion.castra :refer [defrpc]]))


(def options [{:title "core.async"
               :description "Hoare's Communicating Sequential Processes - like go-lang"}
              {:title "core.logic"
               :description "miniKanren logic programming - like a mini-Prolog"}
              {:title "core.typed"
               :description "Gradual typing for Clojure"}])

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

