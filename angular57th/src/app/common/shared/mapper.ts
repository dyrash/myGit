export class mapper<T> {
    constructor(){}
    jsonToMap(array:any[],prop:string):Map<string,any>{
        var testMap:Map<string,any>=
        Object.getOwnPropertyNames(array[prop])
        .reduce((p, key) => p.set(key,array[prop][key]), new Map());
        console.log(testMap);
        testMap.delete("length");
        return testMap;
       
    }
}
