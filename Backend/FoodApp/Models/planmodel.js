const mongoose= require('mongoose');
const db_link='mongodb+srv://admin:ishan@cluster0.oufzyvz.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0'
mongoose.connect(db_link)
.then(function(db){
  console.log('plan db connected');
})
.catch(function(err){
  console.log(err);
});

const planSchema= mongoose.Schema({
    name:{
        type:String,
        required:true,
        unique:true,
        maxlength:[20, 'Plan name length is more than 20 char']
    },
    duration:{
        type:Number,
        required:true
    },
    price:{
        type:Number,
        required:[true,'Price not entered']
    },
    ratingaverage:{
        type:Number
    },
    
    discount:{
        type:Number,
        validate: [function(){
            return this.discount<40
        },'Discount exceeded']
    }
});

const planModel=mongoose.model('planModel', planSchema);

// (async function createplan(){
//     let plan={
//         name:'Food',
//         duration:30,
//         price:251,
//         ratingaverage:5,
//         discount:25
//     };

//     let data= await planModel.create(plan);
//     console.log(data);
// })();


module.exports=planModel;
