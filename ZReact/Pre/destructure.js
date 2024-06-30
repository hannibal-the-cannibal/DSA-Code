let arr=["Hi","I","am","Shreesh"];

let [a,b,c,d]=arr; // Destructuring 
// console.log(arr);

let obj={
    name:"Shreesh",
    State: "Raj",
    City: "Bikaner"
}

let {name,State,city}= obj;
// console.log(name, State, city);


let obj2={
    name:"Shreesh",
    friends:{
        lastname:"Sharma",
        firstname:"YoYo",
        address:{
            colony:"Samta Nagar"
        }
    }
}


let {friends:{lastname}}= obj2;

let {friends:{address:{colony}}}= obj2;

console.log(colony);
console.log(lastname);