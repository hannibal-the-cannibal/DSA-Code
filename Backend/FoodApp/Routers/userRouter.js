const express= require('express');
const multer= require('multer')
const userRouter= express.Router(); // global middleware function 
const {getUser, getallUsers, patchUser,deleteUser, setcookies, getcookies}= require('../Controller/usercontroller');

const {signup, login, protectRoute, isAuthorised, resetPassword, logout, forgetPassword}= require('../Controller/authcontroller');

// const protectRoute= require('./authHelper');
const cookieparser= require('cookie-parser');
const app= express();


//useroptions
userRouter.route('/:id')
.patch(patchUser)
.delete(deleteUser)


userRouter.route('/signup')
.post(signup)


userRouter.route('/login')
.post(login)


//profile page
userRouter.route('/userProfile')
.get(protectRoute, getUser)


//admin specific
userRouter.route('/')
.get(isAuthorised(['admin']), getallUsers) 

userRouter
.route('/forgetpassword')
.post(forgetPassword);

userRouter
.route('/resetpassword/:token')
.post(resetPassword);


const multerStorage=  multer.diskStorage({
    destination: function(req,file,cb){
        cb(null, 'C:\Users\bhardwajs\OneDrive - Crowe LLP\Desktop\dsa code\Backend\FoodApp\public\images')
    },
    filename: function(req,file,cb){
        cb(null, `user-${Date.now()}.jpeg`)
    }
})

const filter= function  (req, file, cb){
    if(file.mimetype.startsWith("image")){
        cb(null,true)
    }else{
        cb(new Error("Not an image"))
    }
}
const upload = multer({
    storage: multerStorage,
    filefilter: filter
})





userRouter
.route('/logout')
.get(logout)



module.exports= userRouter;

