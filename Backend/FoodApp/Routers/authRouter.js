const express= require('express');
const authRouter= express.Router();

const jwt = require('jsonwebtoken');
const JWT_KEY= require('../secret')

const userModel= require('../Models/usermodel');
const cookieparser= require('cookie-parser');
const app= express();
app.use('/', authRouter);

authRouter
.route('/signup')
.get(middleware,getSignup)
.post(postSignup)

authRouter
.route('/login')
.post(loginUser);

async function getSignup(req,res){
    res.sendFile('/public/index.html',{root:__dirname});
}

async function postSignup(req,res){
    let dataobj= req.body;
    let userdata= await userModel.create(dataobj);
   res.json({
    message:"user signed up",
    data : userdata
   })
}

function middleware(req,res,next)
{
    console.log('middle called');
    next();
}

async function loginUser(req,res){
    try{
        let data= req.body;
        // also we can check if data has email or its is blank
        let user= await userModel.findOne({email:data.email});
        if(user)
        {
            //bcrypt->compare 
            if(user.password==data.password)
            {
                // removing cookies and now doing json work
                // res.cookie('isLoggedIn', true);

                let uid= user._id //uid
                let jwttoken = jwt.sign({payload:uid},JWT_KEY);
                res.cookie('login', jwttoken);
                return res.json({
                    message:'Successfully logged in',
                    userDetails: data
                })
            }
            else
            {
                return res.json({
                    message:'Wrong Password '
                })
            }
        }
        else{
            return res.json({
                message:'User Not Found'
            })
        }
    }

    catch(err){
        return res.json({
            message:err.message
        })
    }
}


module.exports=authRouter;