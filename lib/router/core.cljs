(ns router.core
  "Provides helpers to help dealing with Secretary by using browser history."
  (:require [reagent.core :as reagent]
            [secretary.core :as secretary]
            [metadata :refer [add-key-metadata]]
            [re-frame.core :as re-frame]))

(defn- get-pathname! []
  (.-pathname (.-location js/window)))

;; This atom is there to force nav links to reload and get first page.
(def ^:private location (reagent/atom nil))

(defn navigate-to!
  ([to state]
   (.pushState (.-history js/window) state "" to)
   (reagent/rswap! location (constantly to))
   (secretary/dispatch! to))
  ([to]
   (navigate-to! to nil)))

(defn- navigate-to-help! [to state]
  (fn [event]
    (.preventDefault event)
    (navigate-to! to state)))

;; Creates a link using an <a> tag with correct href. The default behavior
;;  is prevented. It can accpt custom classes, style and state for pushState
;;  if needed. Not so much useful, but available in the browser.
(defn link [props & content]
  (let [{:keys [to class style state]} (when (map? props) props)
        child-list (if (map? props) content (cons props content))]
    [:a
     {:class class
      :style style
      :href to
      :on-click (navigate-to-help! to state)}
     (add-key-metadata child-list)]))

(defn- add-active-class [{:keys [to active-class-name]} classes-names]
  (if (nil? to)
    classes-names
    (let [pathname @location
          active-class (when (= pathname to) (or active-class-name "active"))]
      (if (string? classes-names)
        [classes-names active-class]
        (conj classes-names active-class)))))

;; Creates a link which automatically add an active class to itself when the
;;  pathname is equal to the destination. You can define the active class name
;;  by setting :active-class-name in the options. Otherwise, it has the same
;;  behavior and options than a regular link.
(defn nav-link [options & content]
  (let [classes-names (:class options)
        new-classes (add-active-class options classes-names)]
    (apply link (cons (assoc options :class new-classes) content))))

(defn init! []
  (let [pathname (get-pathname!)]
    (js/window.addEventListener "popstate" #(secretary/dispatch! (get-pathname!)))
    (secretary/dispatch! pathname)
    (reagent/rswap! location (constantly pathname))))

(defn reload! []
  (reagent/rswap! location (constantly (get-pathname!))))
