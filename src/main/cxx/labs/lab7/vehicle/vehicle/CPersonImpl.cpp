#include "CPersonImpl.h"

template <typename Base>
CPersonImpl<Base>::CPersonImpl(std::string const& name)
{
    m_name = name;
}

template <typename Base>
CPersonImpl<Base>::~CPersonImpl() = default;

template <typename Base>
std::string CPersonImpl<Base>::GetName() const
{
    return std::string(m_name);
}
