const mongoose= require('mongoose');
const db_link='mongodb+srv://admin:ishan@cluster0.oufzyvz.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0'
mongoose.connect(db_link)
.then(function(db){
  console.log('review  db connected');
})
.catch(function(err){
  console.log(err);
});

const reviewSchema= mongoose.Schema({
    review:{
        type:String,
        required: [true,'review daal de bhai']
    },
    rating:{
        type:Number,
        min:1,
        max:10,
        required:[true, 'rating de de bhai']
    },
    createdAt:{
        type:Date,
        default:Date.now()
    },
    user:{
        type: mongoose.Schema.ObjectId,
        ref: 'userModel',
        required:[true,'review must belong to user'],

    },

    plan:{
        type: mongoose.Schema.ObjectId,
        ref: 'planModel',
        required:[true,'review must belong to plan']
    }
})

reviewSchema.pre(/^find/, function(next)
{
    this.populate({
        path:"user",
        select: "name profileimg "
    }).populate("plan");
    next();
})

const reviewModel=mongoose.model('reviewModel', reviewSchema);




module.exports=reviewModel;