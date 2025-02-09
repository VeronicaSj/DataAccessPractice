import { LoadingButton } from '@mui/lab';
import { Box, Container, TextField, Typography } from '@mui/material';
import React, { useCallback, useEffect } from 'react';
import { useState, useRef } from 'react'
import WeatherCard from './weatherCard';

function ConsultWeather ({city, renderData}) {
    const API_WEATHER = `https://api.weatherapi.com/v1/current.json?key=${'d366186cb54f4e5ebf520413250902'}&q=`;
    const [weather, setWeather] = useState(null)
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)

		useEffect(() => {
			const fetchData = async () => {
				try{
                    if( (!city.trim()) ) throw { message: "El campo ciudad es obligatorio"};
                    const response = await fetch( API_WEATHER + city );
                    const data = await response.json();
                    
                    setWeather ({
                        city: data.location.name,
                        country: data.location.country,
                        temp: data.current.temp_c,
                        condition: data.current.condition.code,
                        icon: data.current.condition.icon,
                        conditionText: data.current.condition.text,
                    });

                    if( data.error ) throw { message: data.error.message };
				} catch (error) {
					setError(error.message)
				} finally {
					setLoading(false)
				}
			}
			fetchData();
            
		},[])

    if (loading) return <p>Cargando...</p>
    if (error) return <p>Ha ocurrido un error: {error}</p>

	return (
		<>
			{renderData(weather)}
		</>
	)
    
}

export default ConsultWeather;
