import { useState, useEffect } from "react";

export default function useWeatherApi(city) { 

    
    const API_WEATHER = `https://api.weatherapi.com/v1/current.json?key=${'59b0a04d42264d33861121600251102'}&q=`;

    const [error, seterror] = useState(false);
    const [msg, setmsg] = useState(null);
    const [data, setdata] = useState(city);
    const [loading, setloading] = useState(null);

    useEffect(() => {
    const fetchData = async () => {
        setloading(true);
        seterror(false);
        try{
            if( (!city.trim()) ) throw { message: "El campo ciudad es obligatorio"};
            const response = await fetch( API_WEATHER + city );
            const data = await response.json();
            console.log(data)
            setdata ({
                city: data.location.name,
                country: data.location.country,
                temp: data.current.temp_c,
                condition: data.current.condition.code,
                icon: data.current.condition.icon,
                conditionText: data.current.condition.text,
            });
            console.log('wtf2')
            if( data.error ) throw { message: data.error.message };
        } catch (error) {
            seterror(true);
            setmsg(error.message) ;
        } finally {
            setloading(false);
        }
    }
    fetchData();
    },[city])

  return {
    error:error,
    msg:msg,
    data:data,
    loading:loading,
  };
}