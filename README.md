# Re-Frame Template

This repo aims to provide a simple, classic re-frame template for ClojureScript.

# Getting Started

```bash
git clone git@github.com:ghivert/re-frame-template.git my-app
cd my-app
rm -rf .git
git init
```

Then, you'll have to rename the template name. `re_frame_template` should become
`my_app` and `re-frame-template` should become `my-app`.

# CSS Modules

This template uses [`modular-styles`](https://github.com/ghivert/modular-styles)
to compute the CSS. To get an overview of what’s happening (because the resulting
files are not commited), please, run `yarn dev-css` before anything else.
