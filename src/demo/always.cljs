(ns ^:dev/always demo.always
    (:require [demo.script :as script]))

(defn main [& args]
  (script/start!))

(comment
  (main)
  (script/stop!)
  :rcf)
