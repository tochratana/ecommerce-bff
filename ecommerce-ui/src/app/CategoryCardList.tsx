"use client"
import {useEffect} from "react";

export function CategoryCardList(){


    useEffect(()=>{
        function fetchCategory(){
            fetch("/ecommerce-api/api/v1/category")
                .then(res=>res.json())
                .then(json=>{
                    console.log("Category : ", json)
                })
        }
        fetchCategory()
    },[])
    return <div>
        <button>Create Category</button>
    </div>
}