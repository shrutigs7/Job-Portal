// src/Router.jsx
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from '../pages/Login';
import AdminDashboard from '../pages/admin/AdminDashboard';
import RecruiterDashboard from '../pages/recruiter/RecruiterDashboard';
import CandidateDashboard from '../pages/candidate/CandidateDashboard';
import AdminHome from '../components/admin/AdminHome';
import AdminJobs from '../components/admin/AdminJobs';
import AdminUsers from '../components/admin/AdminUsers';

import PostJob from '../components/recruiter/PostJob';
import RecruiterProfile from '../components/recruiter/RecruiterProfile';
import AllPostedJobs from '../components/recruiter/AllPostedJobs';
import UpdateJob from '../components/recruiter/UpdateJob';
import AppliedCandidates from '../components/recruiter/AppliedCandidates';
import ReviewApplicant from '../components/recruiter/ReviewApplicant';
import RecruiterRegister from '../components/recruiter/RecruiterRegister';
import CandidatePersonalInfo from '../components/candidate/CandidatePersonalInfo';
import CandidateEducation from '../components/candidate/CandidateEducation';
import CandidateExperience from '../components/candidate/CandidateExperience';
import CandidateSkills from '../components/candidate/CandidateSkills';
import ViewApplications from '../components/candidate/ViewApplications';
import SearchJobs from '../components/candidate/SearchJobs';
import CandidateProfile from '../components/candidate/CandidateProfile';
import UpdateCandidatePersonalInfo from '../components/candidate/UpdateCandidatePersonalInfo';

import Signup from '../pages/SignUp';

function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/login" element={<Login />} />


            {/* Admin Dashboard Routes */}
            <Route path="/admin" element={<AdminDashboard />}>
                <Route path="home" element={<AdminHome />} />
                <Route path="jobs" element={<AdminJobs />} />
                <Route path="users" element={<AdminUsers />} />
            </Route>

            {/* Recruiter Dashboard Routes */}
            <Route path="/recruiter" element={<RecruiterDashboard />}>
                <Route path="post-job" element={<PostJob />} />
                <Route path="profile" element={<RecruiterProfile />} />
                <Route path="all-jobs" element={<AllPostedJobs />} />
                <Route path="all-jobs/:jobId/update" element={<UpdateJob />} />
                <Route path="all-jobs/:jobId/candidates" element={<AppliedCandidates />} />
                <Route path="review-applicant/:userId" element={<ReviewApplicant />} />
            </Route>


            {/* Candidate (assumed flat for now) */}
            <Route path="/candidate/register/personal" element={<CandidatePersonalInfo />} />
            <Route path="/candidate/register/education" element={<CandidateEducation />} />
            <Route path="/candidate/register/experience" element={<CandidateExperience />} />
            <Route path="/candidate/register/skills" element={<CandidateSkills />} />
            <Route path="/candidate" element={<CandidateDashboard />} >
                <Route path="view-applications" element={<ViewApplications />} />
                <Route path="search-jobs" element={<SearchJobs />} />
                <Route path="profile" element={<CandidateProfile />}  />
                <Route path="update/personal" element={<UpdateCandidatePersonalInfo />} />
                <Route path="update/education" element={<CandidateEducation />} />
                <Route path="update/experience" element={<CandidateExperience />} />
            
            </Route>


            {/* Other */}
            <Route path="/signup" element={<Signup />} />
            <Route path="/recruiter/register" element={<RecruiterRegister />} />

            <Route path="/forgot-password" element={<div>Forgot Password</div>} />
            <Route path="*" element={<div>404 - Page Not Found</div>} />
        </Routes>
    );
}

export default AppRoutes;
