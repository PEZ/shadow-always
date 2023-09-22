(ns demo.script
  (:require ["readline" :as readline]))

(defonce !rl (atom nil))

(defn start! []
  (println "starting “server”")
  (reset! !rl (.createInterface readline #js {:input (.-stdin js/process)
                                              :output (.-stdout js/process)
                                              :terminal false}))
  (let [rl ^js @!rl]
    (.setPrompt rl "Enter something: ")
    (.prompt rl)
    (.on rl "line" (fn [line]
                     (println (str "You entered: " line))
                     (.prompt rl)))))

(defn stop! []
  (println "stopping")
  (when-let [rl @!rl]
    (.close rl)
    (reset! !rl nil)))
