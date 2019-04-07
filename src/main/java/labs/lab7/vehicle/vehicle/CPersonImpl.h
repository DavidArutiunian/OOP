#pragma once
#include <string>

template <typename Base>
class CPersonImpl : public Base
{
    const std::string m_name;

public:
    CPersonImpl(std::string const& name);

    ~CPersonImpl();

    std::string GetName() const;
};
