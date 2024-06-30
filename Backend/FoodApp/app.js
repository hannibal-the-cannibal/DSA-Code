const express= require('express');
const { default: mongoose } = require('mongoose');

const app= express();



app.get('/', (req, res)=>{
    res.sendFile('C:/Users/bhardwajs/OneDrive - Crowe LLP/Desktop/dsa code/Backend/ExpressFile/Basics/index.html');
  })

  app.get('/about', (req, res) => {
    res.sendFile('./Basics/about.html', { root: __dirname });
  });

app.listen(3000);

app.get('/about-me',(req,res)=>{
    res.redirect('/about')
})

app.use((req,res)=>{
  // middleware function
    res.status(404).sendFile('./Basics/404.html', { root: __dirname });
})



