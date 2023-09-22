(ns demo.user
  (:require [clojure.pprint]))

(defn print-compiled-files {:shadow.build/stages #{:flush}}
  [{:keys [build-sources] :as build-state}]
  (let [compiled (get-in build-state [:shadow.build/build-info :compiled])
        compiled-resources (for [src-id build-sources
                                 :when (contains? compiled src-id)
                                 :let [rc (get-in build-state [:sources src-id])]]
                             (:resource-name rc))]
    (tap> build-state) ; for debugging incorrect shadow compiles
    (binding [clojure.pprint/*print-right-margin* 20]
      (clojure.pprint/pprint [:compiled (sort compiled-resources)]))
    build-state))