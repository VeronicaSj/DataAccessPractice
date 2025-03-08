
import React, { useCallback, useEffect } from 'react';
import { useState, useRef } from 'react'
import WeatherCard from './weatherCard';
import useWeatherApi from '../hooks/useWeatherApi';

function ConsultWeather ({city}) {
    let apiRes = useWeatherApi(city)

    if (apiRes.loading) return null
    if (apiRes.error) return <p>Ha ocurrido un error: {apiRes.msg}</p>

    return (
        <>
          <WeatherCard weather={apiRes.data}/>
        </>
    )
}

export default ConsultWeather;
