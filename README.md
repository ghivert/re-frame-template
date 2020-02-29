# Re-Frame Template

This repo aims to provide a simple, classic re-frame template for ClojureScript.

# Getting Started

## Cloning the repo

```bash
git clone git@github.com:ghivert/re-frame-template.git my-app
cd my-app
rm -rf .git
git init
```

## Starting the app

```bash
yarn
yarn dev
```

You can go to [`http://localhost:7070`](http://localhost:7070).

Then, you'll have to rename the template name. `re_frame_template` should become
`my_app` and `re-frame-template` should become `my-app`.

# CSS Modules

This template uses [`modular-styles`](https://github.com/ghivert/modular-styles)
to compute the CSS. Please run `yarn dev` to see what’s happening.

To give an example, let’s take the following CSS:

```css
/* File.css */
.test {
  color: red;
}
```

When compiled, a ClojureScript file will be emitted.

```clojure
(ns re-frame-template.styles.file)

(def test "__test_xed87")
```

You can then use it in your code to get the correct classes names !
