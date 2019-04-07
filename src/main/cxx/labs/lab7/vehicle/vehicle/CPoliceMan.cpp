#include "CPoliceMan.h"

CPoliceMan::CPoliceMan(std::string const& name, std::string const& departmentName) : CPersonImpl(name),
                                                                                     m_departmentName(departmentName)
{
}

CPoliceMan::~CPoliceMan() = default;

std::string CPoliceMan::GetDepartmentName() const
{
    return std::string(m_departmentName);
}
