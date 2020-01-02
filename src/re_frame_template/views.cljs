(ns re-frame-template.views
  (:require [re-frame.core :as re-frame]
            [re-frame-template.events :as events]
            [re-frame-template.subs :as subs]))

(defn not-found []
  [:div "404 — Page non trouvée"])

(defn select-active-page []
  (let [active-page (re-frame/subscribe [::subs/active-page])]
    (case @active-page
      [not-found])))

(defn render []
  [:div
   [select-active-page]])
