const express= require('express');
const cookieparser= require('cookie-parser');
const app= express();
const userRouter= require('./Routers/userRouter')
const authRouter= require('./Routers/authRouter')
const planRouter= require('./Routers/planRouter')
const reviewRouter= require('./Routers/reviewRouter')

app.use(express.json()); // global middleware function 
app.listen(3000);
app.use(cookieparser());


// let users=[
//     {
//         'id':1,
//         'name':"Shreesh"
//     },
//     {
//         'id':2,
//         'name':"Manik"
//     }
// ];


// const authRouter= express.Router();
// const userRouter= express.Router();

app.use('/user', userRouter);
app.use('/auth', authRouter);
app.use('/plan', planRouter);
app.use('/review',reviewRouter);





// app.get('/user',(req,res)=>{
//     console.log(req.query);
//     res.send(users);
// })

// app.post('/user', (req,res)=>{
//     console.log(req.body);
//     users=req.body;
//     res.json({
//         message:'Data success',
//         user: req.body
//     });
// });

// app.patch('/user', (req,res)=>{
//     console.log(req.body);
//     users = {...users, ...req.body}
//     res.json({
//         message:"data updated",
//         user: req.body

//     })
// })

// app.delete('/user', (req,res)=>{
//     users={};
//     res.json({
//         message: 'Data deleted'
//     })
// })

// app.get('/user/:name',(req,res)=>{
//     // console.log(req.params.id);
//     console.log(req.params.name)
//     console.log(req.params);
//     res.send("user id is ");
    
// })












