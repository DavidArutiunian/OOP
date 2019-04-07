#pragma once
#include "CPersonImpl.h"
#include "IPoliceMan.h"

class CPoliceMan : public CPersonImpl<IPoliceMan>
{
    const std::string m_departmentName;

public:
    CPoliceMan(std::string const& name, std::string const& departmentName);

    ~CPoliceMan();

    std::string GetDepartmentName() const override;
};
