import React, { useEffect, useState } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import {
    Box,
    Button,
    Checkbox,
    FormControlLabel,
    FormGroup,
    Typography
} from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const JobGrid = () => {

    const userId = JSON.parse(localStorage.getItem('user'))?.userId;
    const [jobs, setJobs] = useState([]);
    const [filteredJobs, setFilteredJobs] = useState([]);
    const [selectedSkills, setSelectedSkills] = useState([]);
    const [selectedExp, setSelectedExp] = useState(null);
    const [allSkills, setAllSkills] = useState([]);
    const [appliedJobIds, setAppliedJobIds] = useState([]);
    const navigate = useNavigate();
    const token = JSON.parse(localStorage.getItem('user'))?.token;
    const [error, setError] = useState(null);


    // Step 1: Fetch applied jobs
    useEffect(() => {

        const headers = {
            Authorization: `Bearer ${token}`,
        };
        if (!userId) return;
        axios.get(`http://localhost:8081/candidate/job/${userId}`, { headers })
            .then(res => {
                const appliedJobs = res.data;
                const appliedIds = appliedJobs.map(job => job.jobId);
                setAppliedJobIds(appliedIds);
            })
            .catch(err => setError(err.message));
    }, [userId]);

    // Step 2: Fetch all jobs and exclude applied ones
    useEffect(() => {

        const headers = {
            Authorization: `Bearer ${token}`,
        };

        axios.get('http://localhost:8081/candidate/jobs', { headers })
            .then(res => {
                const jobList = res.data;

                // Filter out already applied jobs
                const availableJobs = jobList.filter(job => !appliedJobIds.includes(job.jobId));
                setJobs(availableJobs);
                setFilteredJobs(availableJobs);

                // Collect unique skill names
                const skillsSet = new Set();
                availableJobs.forEach(job =>
                    job.jskills.forEach(skill => skillsSet.add(skill.skillName))
                );
                setAllSkills([...skillsSet]);
            })
            .catch(err => setError(err.message));
    }, [appliedJobIds]);

    // Step 3: Filter by skills and experience
    useEffect(() => {
        const filtered = jobs.filter(job => {
            const skillMatch = selectedSkills.length === 0 || job.jskills.some(skill => selectedSkills.includes(skill.skillName));
            const expMatch = selectedExp === null || job.yearOfExperience >= selectedExp;
            return skillMatch && expMatch;
        });
        setFilteredJobs(filtered);
    }, [selectedSkills, selectedExp, jobs]);

    const handleSkillChange = (skill) => {
        setSelectedSkills(prev =>
            prev.includes(skill) ? prev.filter(s => s !== skill) : [...prev, skill]
        );
    };

    const handleDetails = (job) => {
        navigate(`/candidate/job/${job.jobId}`, { state: { job } });
    };

    const columns = [
        { field: 'title', headerName: 'Job Title', flex: 1 },
        { field: 'companyName', headerName: 'Company', flex: 1 },
        { field: 'yearOfExperience', headerName: 'Yrs Exp', type: 'number', flex: 1 },
        { field: 'type', headerName: 'Type', flex: 1 },
        { field: 'location', headerName: 'Location', flex: 1 },
        {
            field: 'jskills',
            headerName: 'Skills',
            flex: 1.5,
            renderCell: (params) => params.row.jskills.map(s => s.skillName).join(', ')
        },
        {
            field: 'detail',
            headerName: 'Details',
            renderCell: (params) => (
                <Button variant="contained" size="small" onClick={() => handleDetails(params.row)}>
                    Details
                </Button>
            ),
            sortable: false,
            filterable: false,
            width: 100
        }
    ];

    return (

        <Box sx={{ padding: 3 }}>
            {error && (
                <div style={{ color: 'red', marginBottom: '1rem' }}>
                    {error}
                </div>
            )}
            <Typography variant="h5" gutterBottom>Job Listings</Typography>

            {/* Filters */}
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

                <Typography variant="subtitle1" sx={{ mt: 2 }}>
                    Filter by Minimum Years of Experience:
                </Typography>
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
                </FormGroup>
            </Box>

            {/* Data Grid */}
            <div style={{ height: 500, width: '100%' }}>
                <DataGrid
                    rows={filteredJobs.map((job, i) => ({ id: job.jobId, ...job }))}
                    columns={columns}
                    pageSize={5}
                    rowsPerPageOptions={[5, 10]}
                />
            </div>
        </Box>
    );
};

export default JobGrid;
