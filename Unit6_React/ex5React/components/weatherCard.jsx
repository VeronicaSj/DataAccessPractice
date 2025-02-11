
import { Box, Typography } from '@mui/material';
import React from 'react';

function WeatherCard( {weather}) {
    console.log(weather)
    
    return (<Box sx={{mt:2,display:"grid",gap:2,textAlign:"center"}}>
        <Typography variant='h4' component='h2'>
            {weather.city},{weather.country}
        </Typography>
        <Box component='img' alt="mkdkvmlk" src={weather.icon} sx={{ margin: "0 auto"}}/>
        <Typography variant='h2' component='h2'>
            {weather.temp}ºC
        </Typography>
        <Typography variant='h5' component='h5'>
            {weather.conditionText}
        </Typography>
    </Box>
    );
    
  }
  
  export default WeatherCard;