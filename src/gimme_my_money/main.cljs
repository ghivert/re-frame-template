(ns gimme-my-money.main
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [gimme-my-money.config :as config]
            [gimme-my-money.views :as views]
            [gimme-my-money.events :as events]
            [gimme-my-money.routes :as routes]
            [router]))

(defn dev-setup! []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root! []
  (let [mount-div (.getElementById js/document "app")]
    (re-frame/clear-subscription-cache!)
    (reagent/render [views/render] mount-div)))

(defn ^:export main! []
  (routes/app-routes)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup!)
  (router/init!)
  (mount-root!))

(defn reload! []
  (router/reload!)
  (reagent/force-update-all))
