(ns app.rpc
  (:require-macros
   [tailrecursion.javelin :refer [defc defc=]])
  (:require
   [tailrecursion.javelin]
   [tailrecursion.castra :refer [mkremote]]))

(defc state nil)
(defc error nil)
(defc loading [])

(def get-state
  (mkremote 'app.api/get-state state error loading))

(def vote!
  (mkremote 'app.api/vote! state error loading))

(defn init []
  (get-state)
  (js/setInterval get-state 1000))
