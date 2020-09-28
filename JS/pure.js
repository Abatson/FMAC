let externalVar = 0;


function impureAdd(a, b) {

    return a + b + externalVar++
}

function pureAdd(a,b,c){
    
    return a+b+c
}



console.log(`a=1 b=2 externalVar=${externalVar} pureAdd = ${pureAdd(1,2,externalVar)}, impureAdd = ${impureAdd(1,2)}`);
console.log(`a=1 b=2 externalVar=${externalVar} pureAdd = ${pureAdd(1,2,externalVar)}, impureAdd = ${impureAdd(1,2)}`);
console.log(`a=1 b=2 externalVar=${externalVar} pureAdd = ${pureAdd(1,2,externalVar)}, impureAdd = ${impureAdd(1,2)}`);
console.log(`a=1 b=2 externalVar=${externalVar} pureAdd = ${pureAdd(1,2,0)}, impureAdd = ${impureAdd(1,2)}`);
console.log(`a=1 b=2 externalVar=${externalVar} pureAdd = ${pureAdd(1,2,1)}, impureAdd = ${impureAdd(1,2)}`);
console.log(`a=1 b=2 externalVar=${externalVar} pureAdd = ${pureAdd(1,2,2)}, impureAdd = ${impureAdd(1,2)}`);

