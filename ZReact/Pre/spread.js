let obj={
    name:"shreesh",
    address:{
        pin:"334002",
        city:"Bikaner"
    }
}

let obj3= {...obj, address:{...obj.address}};

// let obj2={...obj}; //shallow copy 
// obj2.address={...obj.address} //Deep Copy 
// obj2.address.pin="334001";


let obj2= JSON.parse(JSON.stringify(obj));
obj2.address.pin="334001";


console.log(obj);
console.log(obj2);