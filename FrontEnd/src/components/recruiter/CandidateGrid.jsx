// CandidateGrid.jsx
import React, { useEffect, useState } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import {
  Box, Button, Checkbox, FormControlLabel, FormGroup,
  Typography, Link, Stack
} from '@mui/material';
import axios from 'axios';

const CandidateGrid = () => {
  const [candidates, setCandidates] = useState([]);
  const [filteredCandidates, setFilteredCandidates] = useState([]);
  const [allSkills, setAllSkills] = useState([]);
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [selectedExp, setSelectedExp] = useState(null);
  const [error, setError] = useState(null);
  const token = JSON.parse(localStorage.getItem('user'))?.token;


  useEffect(() => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    axios.get('http://localhost:8081/recruiter/candidates' , { headers })
      .then(res => {
        const data = res.data;

        setCandidates(data);
        setFilteredCandidates(data);

        // Collect unique skills
        const skillSet = new Set();
        data.forEach(c => c.skills.forEach(s => skillSet.add(s)));
        setAllSkills([...skillSet]);
      })
      .catch(err => setError(err.message));
  }, []);

  useEffect(() => {
    const filtered = candidates.filter(candidate => {
      const skillMatch = selectedSkills.length === 0 || candidate.skills.some(skill => selectedSkills.includes(skill));
      const expMatch = selectedExp === null || candidate.experience >= selectedExp;
      return skillMatch && expMatch;
    });
    setFilteredCandidates(filtered);
  }, [selectedSkills, selectedExp, candidates]);

  const handleSkillChange = (skill) => {
    setSelectedSkills(prev =>
      prev.includes(skill) ? prev.filter(s => s !== skill) : [...prev, skill]
    );
  };

  const columns = [
    { field: 'name', headerName: 'Name', flex: 1 },
    {
      field: 'dateOfBirth',
      headerName: 'DOB',
      flex: 1,
    },
    {
      field: 'linkedIn',
      headerName: 'LinkedIn',
      flex: 1,
      renderCell: (params) => (
        <Link href={params.row.linkedIn} target="_blank" rel="noopener noreferrer">
          LinkedIn
        </Link>
      )
    },
    {
      field: 'gitHub',
      headerName: 'GitHub',
      flex: 1,
      renderCell: (params) => (
        <Link href={params.row.gitHub} target="_blank" rel="noopener noreferrer">
          GitHub
        </Link>
      )
    },
    {
      field: 'skills',
      headerName: 'Skills',
      flex: 1.5,
      renderCell: (params) => (
        <Stack direction="row" spacing={1}>
          {params.row.skills.map((skill, index) => (
            <Typography variant="body2" key={index}>{skill}</Typography>
          ))}
        </Stack>
      )
    }
  ];

  return (
    <Box sx={{ p: 3 }}>
      {error && (
        <div style={{ color: 'red', marginBottom: '1rem' }}>
          {error}
        </div>
      )}
      <Typography variant="h5" gutterBottom>Candidate Listings</Typography>

      {/* Filter section */}
      <Box sx={{ mb: 2 }}>
        <Typography variant="subtitle1">Filter by Skills:</Typography>
        <FormGroup row>
          {allSkills.map(skill => (
            <FormControlLabel
              key={skill}
              control={
                <Checkbox
                  checked={selectedSkills.includes(skill)}
                  onChange={() => handleSkillChange(skill)}
                />
              }
              label={skill}
            />
          ))}
        </FormGroup>

        {/* Optional: Filter by experience if you include it */}
        {/* <Typography variant="subtitle1" sx={{ mt: 2 }}>Minimum Years of Experience:</Typography>
        <FormGroup row>
          {[1, 2, 3, 4, 5].map(exp => (
            <FormControlLabel
              key={exp}
              control={
                <Checkbox
                  checked={selectedExp === exp}
                  onChange={() => setSelectedExp(selectedExp === exp ? null : exp)}
                />
              }
              label={`${exp}+ yrs`}
            />
          ))}
        </FormGroup> */}
      </Box>

      <div style={{ height: 500, width: '100%' }}>
        <DataGrid
          rows={filteredCandidates.map((candidate, i) => ({ id: i, ...candidate }))}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[5, 10]}
        />
      </div>
    </Box>
  );
};

export default CandidateGrid;
