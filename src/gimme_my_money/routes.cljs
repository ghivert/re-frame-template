(ns gimme-my-money.routes
  (:require [re-frame.core :as re-frame]
            [secretary.core :as secretary :refer-macros [defroute]]
            [gimme-my-money.events :as events]
            [router]))

(defn app-routes []
  (defroute "/" []
    (re-frame/dispatch [::events/navigate-to :home-page]))
  (defroute "/login" []
    (re-frame/dispatch [::events/navigate-to :login-page]))
  (defroute "/messages" []
    (re-frame/dispatch [::events/navigate-to :messages-page]))
  (defroute "/projects" []
    (re-frame/dispatch [::events/navigate-to :projects-page]))
  (defroute "/schedule" []
    (re-frame/dispatch [::events/navigate-to :schedule-page]))
  (defroute "/activity" []
    (re-frame/dispatch [::events/navigate-to :activity-page]))
  (defroute "/settings" []
    (re-frame/dispatch [::events/navigate-to :settings-page]))
  (defroute "*" []
    (re-frame/dispatch [::events/navigate-to :not-found])))
