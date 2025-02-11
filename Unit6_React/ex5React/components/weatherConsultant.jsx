import { LoadingButton } from '@mui/lab';
import { Box, Container, TextField, Typography } from '@mui/material';
import React, { useCallback, useEffect } from 'react';
import { useState, useRef } from 'react'
import WeatherCard from './weatherCard';

function ConsultWeather ({city, renderData}) {
    const API_WEATHER = `https://api.weatherapi.com/v1/current.json?key=${'59b0a04d42264d33861121600251102'}&q=`;
    const [weather, setWeather] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

    console.log({city})

		useEffect(() => {
        console.log('wtf')
        const fetchData = async () => {
            setLoading(true)
            setError(null)
            try{
                if( (!city.trim()) ) throw { message: "El campo ciudad es obligatorio"};
                const response = await fetch( API_WEATHER + city );
                const data = await response.json();
                console.log(data)
                setWeather ({
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
              setError(error.message)
            } finally {
              setLoading(false)
            }
        }
        fetchData();
		},[city])

    if (loading) return null
    if (error) return <p>Ha ocurrido un error: {error}</p>

    return (
        <>
          {renderData(weather)}
        </>
    )
}

export default ConsultWeather;
