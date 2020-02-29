const sleep = async number => {
  return new Promise(resolve =>
    setTimeout(resolve, number)
  )
}

module.exports = {
  sleep,
}
