(ns gimme-my-money.views
  (:require [re-frame.core :as re-frame]
            [gimme-my-money.views.navbar :refer [navbar]]
            [gimme-my-money.views.sidebar :refer [sidebar]]
            [gimme-my-money.views.login :refer [login]]
            [gimme-my-money.views.dashboard :refer [dashboard]]
            [gimme-my-money.events :as events]
            [gimme-my-money.subs :as subs]
            [gimme-my-money.styles.common :as styles]
            [gimme-my-money.styles.spaces :as spaces]
            [gimme-my-money.styles.colors :as colors]
            [gimme-my-money.styles.fonts :as fonts]
            [gimme-my-money.styles.link :as link]
            [gimme-my-money.styles.layout :as layout]
            [gimme-my-money.styles.input :as input]
            [gimme-my-money.styles.button :as button]))

(defn not-found []
  [:div {:class [styles/column styles/center]
         :style {:height "100%"}}
   [:div {:class fonts/subtitle
          :style {:margin "12px"}}
    "404 — Page non trouvée"]
   [:button {:class button/base} "Retour"]])

(defn select-active-page []
  (let [active-page (re-frame/subscribe [::subs/active-page])]
    (case @active-page
      :home-page [dashboard]
      :messages-page [:div]
      :projects-page [:div]
      :schedule-page [:div]
      :activity-page [:div]
      :settings-page [:div]
      :login-page [login]
      [not-found])))

(defn logo []
  [:div {:class layout/logo} "Ublo"])

(defn connected-layout []
  [:div {:class layout/main-nav-sidebars}
   [logo]
   [navbar]
   [sidebar]
   [:main {:class layout/main} (select-active-page)]])

(defn disconnected-layout []
  (let [active-page (re-frame/subscribe [::subs/active-page])]
    (case @active-page
      :login-page [login]
      [not-found])))

(defn render-page []
  (let [connected-page (re-frame/subscribe [::subs/connected-page])]
    (if @connected-page
      [connected-layout]
      [disconnected-layout])))

(defn render []
  [:div {:class styles/app}
   [render-page]])
