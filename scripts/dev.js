const child = require('child_process')
const modularStyles = require('modular-styles')

const logger = require('./helpers/console')

const stylesOptions = require('../config/styles')

const dev = async () => {
  // Watch styles.
  modularStyles.watch(stylesOptions)

  // Spawn process
  const shadow = child.spawn('yarn', ['shadow-cljs', 'watch', 'app'])
  shadow.stdout.on('data', logger(console.log))
  shadow.stderr.on('data', logger(console.error))
  shadow.on('close', code => process.exit())
  process.on('SIGINT', () => shadow.kill('SIGINT'))
}

dev()
