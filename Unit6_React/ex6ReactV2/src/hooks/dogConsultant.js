import React, { useState, useEffect } from "react";

export default function useDogConsultant(dog) { 

  console.log("useDogConsultant "+ dog)

  
  let APIendpoint = 'https://dog.ceo/api/breed/'+ dog+'/images/random';

    const [error, seterror] = useState(false);
    const [msg, setmsg] = useState(null);
    const [data, setdata] = useState(dog);
    const [loading, setloading] = useState(false);

    useEffect(() => {
    console.log('wtf')
    const fetchData = async () => {
        setloading(true);
        seterror(false);
        try{
            if( (dog == null || !dog.trim()) ) throw { message: "El campo raza es obligatorio"};
            const response = await fetch( APIendpoint );
            const data = await response.json();
            console.log(data.message)
            setdata(data.message)
            console.log('wtf2')
            if( data.status== 'error') throw { message: data.message };
        } catch (error) {
            seterror(true);
            setmsg(error.message) ;
        } finally {
            setloading(false);
        }
    }
    fetchData();
    },[dog])

  return {
    error:error,
    msg:msg,
    data:data,
    loading:loading,
  };
}