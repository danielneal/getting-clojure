#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.3.1"

(set-env!
  :project      'functional-brighton-clojure
  :version      "0.1.0-SNAPSHOT"
  :dependencies '[[tailrecursion/boot.task   "2.1.3"]
                  [tailrecursion/hoplon      "5.8.3"]
                  [org.clojure/clojurescript "0.0-2202"]
                  [tailrecursion/boot.ring   "0.1.0"]
                  [tailrecursion/castra "1.2.0"]]
  :out-path     "resources/public"
  :src-paths    #{"src/hl" "src/cljs" "src/clj"})

;; Static resources (css, images, etc.):
(add-sync! (get-env :out-path) #{"assets"})

(require '[tailrecursion.hoplon.boot :refer :all]
         '[tailrecursion.castra.task :as c])

(deftask development
  "Build app and talk for development."
  []
  (comp (watch) (hoplon {:prerender false}) (c/castra-dev-server 'app.api)))

(deftask production
  "Build app and talkfor production."
  []
  (hoplon {:optimizations :advanced}))
