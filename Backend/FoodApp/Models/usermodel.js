//mongodb
const mongoose= require('mongoose');
const emailValidator= require('email-validator');
const bcrypt= require('bcrypt');
const cookieparser= require('cookie-parser');
const crypto= require('crypto');


const db_link='mongodb+srv://admin:ishan@cluster0.oufzyvz.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0'

mongoose.connect(db_link)
.then(function(db){
  console.log('db connected');
})
.catch(function(err){
  console.log(err);
});

const userSchema= mongoose.Schema({
    name:{
        type:String,
        required:true
    },
    email:{
        type:String,
        required:true,
        unique:true,
        validate: function(){
            return emailValidator.validate(this.email);
        }
    },
    password:{
        type: String,
        required:true,
        minLength:4
    },
    confirmpassword:{
        type: String,
        required:true,
        minLength:4,
        validate: function(){
            return this.confirmpassword==this.password
        }
    },
    roles:{
        type:String,
        enum:['admin','user','hotelowner','delivery'],
        default:'user'
    },
    profileimg:{
        type:String,
        default:'img/users/default.jpeg' // use multer here
    },
    resettoken:String
});


// pre-post hooks

// userSchema.pre('save', function(){
//     console.log('before saving in db');
// })

// userSchema.post('save', function(doc){
//     console.log('After saving in db', doc);
// })

//model

userSchema.pre('save', function(){
    this.confirmpassword=undefined;
})

// userSchema.pre('save', async function(){
//     let salt= await bcrypt.genSalt();
//     let hashedString = await bcrypt.hash(this.password,salt);
//     // console.log(hashedString);
//     this.password=hashedString
// })





userSchema.methods.createResetToken= function(){
    // crypto creating unique
    const resetoken = crypto.randomBytes(32).toString('hex');
    this.resettoken= resetoken;

    return resetoken;
}

userSchema.methods.restPasswordHandler= function(password,confirmpassword){
    this.password= password;
    this.confirmpassword= confirmpassword;

    this.resettoken=undefined;


}

const userModel=mongoose.model('userModel', userSchema);
module.exports=userModel;

// (async function createUser(){
//     let user={
//         name:'Jason',
//         email:'abcd@gmail.com',
//         password:'1234',
//         confirmpassword:'1234'
//     };

//     let data= await userModel.create(user);
//     console.log(data);
// })();
