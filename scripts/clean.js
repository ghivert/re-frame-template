const path = require('path')
const util = require('util')
const fs = require('fs').promises

const stylesOptions = require('../config/styles')

const fromRootFolder = pattern => {
  return path.resolve(process.cwd(), pattern)
}

const clean = async () => {
  await fs.rmdir((fromRootFolder('target')), { recursive: true })
  await fs.rmdir((fromRootFolder('public/js')), { recursive: true })
  await fs.rmdir((fromRootFolder('public/css')), { recursive: true })
  await fs.unlink((fromRootFolder('public/styles.css')))
  await fs.rmdir((fromRootFolder(stylesOptions.dest)), { recursive: true })
}

clean()
