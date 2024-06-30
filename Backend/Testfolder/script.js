// express use
const express = require('express')
const app = express(); // we ran express and all power got stored in app

app.set("view engine", "ejs");
app.use(express.static('./public'))

app.use(function(req,res, next){
    console.log("middleware");
    next();
})

app.get('/', function (req, res) {
  res.render('index')
})

app.get('/error', function (req, res) {
    throw Error('Something went wrong');
  })

app.get('/profile/:username', function (req, res) {
    res.render(`Hello from ${req.params.username}`)
  })


  app.use(function errorHandler (err, req, res, next) {
    if (res.headersSent) {
      return next(err)
    }
    res.status(500)
    res.render('error', { error: err })
  })

app.listen(3000)