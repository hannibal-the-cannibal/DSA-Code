let arr= [1,2,3,4,5];

let narr= arr.map((value,idx)=>{
    return value*2;
})

console.log(narr);


let farr=["apple", "mango", "pineapple"];

let newarr= farr.filter(function(value){
    return value.length>6;
})

console.log(newarr);
