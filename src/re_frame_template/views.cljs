(ns re-frame-template.views
  (:require [re-frame.core :as re-frame]
            [re-frame-template.events :as events]
            [re-frame-template.subs :as subs]
            [re-frame-template.styles.views :as styles]))

(def shadow-cljs-logo
  "https://raw.githubusercontent.com/thheller/shadow-cljs/master/src/main/shadow/cljs/devtools/server/web/resources/img/shadow-cljs.png")

(defn link [href text]
  [:a {:href href
       :class styles/padding}
   text])

(defn home-page []
  [:div
   [:header {:class styles/header}
    [:img {:src shadow-cljs-logo}]]
   [:h1 "Welcome to re-frame-template!"]
   [:main {:class styles/main}
    [:p "This template is a combination of multiple technologies mainly based around re-frame and PostCSS.
        In case you need it, you can find documentations below to help you getting started."]
    [:div {:class [styles/row styles/center]}
     [link "https://github.com/day8/re-frame" "re-frame"]
     [link "https://reagent-project.github.io/" "reagent"]
     [link "https://clojurescript.org/" "ClojureScript"]
     [link "https://shadow-cljs.github.io/docs/UsersGuide.html" "Shadow CLJS"]
     [link "https://github.com/ghivert/modular-styles" "Modular Styles"]
     [link "https://postcss.org/" "PostCSS"]]]])

(defn not-found []
  [:div {:class styles/page-not-found} "404 — Page non trouvée"])

(defn select-active-page []
  (let [active-page (re-frame/subscribe [::subs/active-page])]
    (case @active-page
      :home-page [home-page]
      [not-found])))

(defn render []
  [:div
   [select-active-page]])
