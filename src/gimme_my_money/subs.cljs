(ns gimme-my-money.subs
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  ::name
  (fn [db]
    (:name db)))

(re-frame/reg-sub
  ::active-page
  (fn [db]
    (:active-page db)))

(re-frame/reg-sub
  ::connected-page
  :<- [::active-page]
  (fn [active-page]
    (case active-page
      :login-page false
      true)))

(re-frame/reg-sub
  ::menu-opened
  (fn [db]
    (:menu-opened db)))

(re-frame/reg-sub
  ::collaborators
  (fn [db]
    (:collaborators db)))

(re-frame/reg-sub
  ::raw-projects
  (fn [db]
    (:projects db)))

(re-frame/reg-sub
  ::raw-teams
  (fn [db]
    (:teams db)))

(defn find-collaborators [collaborators element]
  (->> collaborators
       (filterv (fn [collaborator]
                  (some (fn [collaborator-id]
                          (= collaborator-id (:id collaborator)))
                        (:collaborators element))))
       (assoc element :collaborators)))

(re-frame/reg-sub
  ::projects
  :<- [::raw-projects]
  :<- [::collaborators]
  (fn [[projects  collaborators]]
    (println "projects: " projects)
    (println "collaborators: " collaborators)
    (map (partial find-collaborators collaborators) projects)))

(re-frame/reg-sub
  ::teams
  :<- [::raw-teams]
  :<- [::collaborators]
  (fn [[teams collaborators]]
    (map (partial find-collaborators collaborators) teams)))
