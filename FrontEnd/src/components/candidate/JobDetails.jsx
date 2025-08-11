import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import {
  Box,
  Typography,
  Button,
  Chip,
  Stack,
  Card,
  CardContent,
  CardActions,
} from '@mui/material';
import axios from 'axios';

const JobDetail = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const job = location.state?.job;

  const userId = JSON.parse(localStorage.getItem('user'))?.userId;
  const token = JSON.parse(localStorage.getItem('user'))?.token;

  const [error, setError] = useState(null);
  if (!job) {
    return <Typography>Job details not found.</Typography>;
  }

  const handleApply = () => {

    const headers = {
      Authorization: `Bearer ${token}`,
    };

    console.log(userId, job.jobId)
    axios
      .post('http://localhost:8081/candidate/job', {
        userId: userId,
        jobId: job.jobId,
      }, { headers })
      .then((res) => {
        alert('Applied successfully!');
        navigate('/candidate/view-applications');
      })
      .catch((err) => {
        setError(err.message);
        alert('Failed to apply.');
      });
  };

  return (
    <Box sx={{ p: 3, display: 'flex', justifyContent: 'center' }}>
      <Card sx={{ maxWidth: 600, width: '100%', p: 2 }}>
        <CardContent>
          <Button variant="text" onClick={() => navigate('/candidate/search-jobs')}>
            ⬅ Back to Job List
          </Button>

          <Typography variant="h4" gutterBottom>
            {job.title}
          </Typography>
          <Typography variant="h6" color="text.secondary">
            {job.companyName}
          </Typography>

          <Typography sx={{ mt: 2 }}>
            <strong>Experience:</strong> {job.yearOfExperience} years
          </Typography>
          <Typography>
            <strong>Type:</strong> {job.type}
          </Typography>
          <Typography>
            <strong>Location:</strong> {job.location}
          </Typography>
          <Typography>
            <strong>Posted Date:</strong> {job.postedDate}
          </Typography>

          <Typography sx={{ mt: 2 }}>
            <strong>Skills:</strong>
          </Typography>
          <Stack direction="row" spacing={1} sx={{ mt: 1, flexWrap: 'wrap' }}>
            {job.jskills.map((skill) => (
              <Chip key={skill.skillId} label={skill.skillName} color="primary" />
            ))}
          </Stack>

          <Typography variant="body1" sx={{ mt: 3 }}>
            {job.description}
          </Typography>
        </CardContent>

        <CardActions>
          <Button variant="contained" color="success" onClick={handleApply}>
            Apply
          </Button>
        </CardActions>
      </Card>
    </Box>
  );
};

export default JobDetail;
