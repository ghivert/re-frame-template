(ns re-frame-template.routes
  (:require [re-frame.core :as re-frame]
            [secretary.core :as secretary :refer-macros [defroute]]
            [re-frame-template.events :as events]))

(defn app-routes []
  (defroute "/" []
    (re-frame/dispatch [::events/navigate-to :home-page]))
  (defroute "*" []
    (re-frame/dispatch [::events/navigate-to :not-found])))
