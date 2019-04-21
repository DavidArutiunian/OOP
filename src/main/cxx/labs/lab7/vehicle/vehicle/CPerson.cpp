#include "CPerson.h"

CPerson::CPerson(std::string const& name)
    : CPersonImpl(name)
{
}

CPerson::~CPerson() = default;
