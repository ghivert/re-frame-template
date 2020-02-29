const child = require('child_process')
const modularStyles = require('modular-styles')

const logger = require('./helpers/console')
const { sleep } = require('./helpers/process')

const stylesOptions = require('../config/styles')

const build = async () => {
  // Watch styles.
  modularStyles.compile(stylesOptions)

  await sleep(5000)

  // Spawn process
  const shadow = child.spawn('yarn', ['shadow-cljs', 'release', 'app'])
  shadow.stdout.on('data', logger(console.log))
  shadow.stderr.on('data', logger(console.error))
  shadow.on('close', code => process.exit())
  process.on('SIGINT', () => shadow.kill('SIGINT'))
}

build()
